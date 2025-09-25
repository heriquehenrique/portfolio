import treinamento
import customtkinter as ctk
import tkinter as tk
from tkinter import scrolledtext
import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.neural_network import MLPClassifier
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics import classification_report, confusion_matrix, accuracy_score
from sklearn import metrics
from time import time
import string

# Mostra o desempenho do modelo MLP no terminal
treinamento.acc_mlp, tt_mlp, tp_mlp = treinamento.mostrar_desempenho(treinamento.x_train_vect, treinamento.y_train,
                                        treinamento.x_test_vect, treinamento.y_test, treinamento.model_mlp, 'MLP')

# Interface em estilo chat com customtkinter
ctk.set_appearance_mode("light")
ctk.set_default_color_theme("green")

# Criação da janela
janela = ctk.CTk()

# Centraliza a janela na tela
largura_janela = 600
altura_janela = 600
largura_tela = janela.winfo_screenwidth()
altura_tela = janela.winfo_screenheight()
pos_x = int((largura_tela / 2) - (largura_janela / 2))
pos_y = int((altura_tela / 2) - (altura_janela / 2))
janela.geometry(f"{largura_janela}x{altura_janela}+{pos_x}+{pos_y}")

# Coloca a janela no topo
janela.attributes('-topmost', True)
janela.after(1000, lambda: janela.attributes('-topmost', False))

# Título da janela
janela.title("Classificador de Notícias - Meio Ambiente")

# Área de mensagens no estilo chat
frame_chat = ctk.CTkScrollableFrame(janela, width=580, height=500)
frame_chat.pack(padx=10, pady=10)

# Entrada para digitar a notícia
entry = ctk.CTkEntry(janela, width=480, placeholder_text="Digite uma notícia...")
entry.pack(side=tk.LEFT, padx=(10,0), pady=(0,10))

# Função para adicionar mensagens no chat
def adicionar_mensagem(texto, origem="usuario"):
    cor = "#DCF8C6" if origem == "usuario" else "#FFFFFF"
    anchor = "e" if origem == "usuario" else "w"
    mensagem = ctk.CTkLabel(frame_chat, text=texto, bg_color=cor, text_color="black", anchor="w", justify="left",
                            width=500, corner_radius=10, fg_color=cor, wraplength=450, padx=10, pady=5)
    mensagem.pack(anchor=anchor, pady=5, padx=5)
    # Scroll automático para o final
    frame_chat._parent_canvas.yview_moveto(1.0)

# Função que responde a notícia digitada
def responder():
    texto = entry.get().strip() # Pega o texto
    if texto == "":
        return
    entry.delete(0, tk.END) # Limpa
    adicionar_mensagem(texto, origem="usuario") # Mostra no chat

    # Pré-processamento e vetorização
    texto_proc = treinamento.preprocessar_texto(texto)
    texto_vect = treinamento.vectorizer.transform([texto_proc])

    # Classificação da notícia
    probas = treinamento.model_mlp.predict_proba(texto_vect)
    predicao = treinamento.model_mlp.predict(texto_vect)
    confianca = np.max(probas)

    # Responde com base na confiança
    if confianca < 0.70:
        resposta = "Notícia irrelevante"
    else:
        resultado = "boa" if predicao[0] == 1 else "ruim"
        tipo_predito = treinamento.model_tipo.predict(texto_vect)[0]
        resposta = f"Esta notícia é **{resultado}**\nTipo de notícia: **{tipo_predito}**\nConfiança: {confianca:.2f}"

    adicionar_mensagem(resposta, origem="bot") # MOstra a resposta

# Botão para enviar a notícia
botao = ctk.CTkButton(janela, text="Enviar", width=80, command=responder)
botao.pack(side=tk.RIGHT, padx=(5,10), pady=(0,10))

import psutil
import socket

# Função para detectar os protocolos de rede em uso
def detectar_protocolos():
    protocolos_usados = set()
    conexoes = psutil.net_connections()
    for c in conexoes:
        if c.type == socket.SOCK_STREAM:
            protocolos_usados.add("TCP")
        elif c.type == socket.SOCK_DGRAM:
            protocolos_usados.add("UDP")
    return ", ".join(protocolos_usados) if protocolos_usados else "Nenhum detectado"

# Rótulo com os protocolos no canto inferior esquerdo
protocolos_texto = f"Protocolos de rede detectados: {detectar_protocolos()}"
label_protocolos = ctk.CTkLabel(janela, text=protocolos_texto, text_color="gray")
label_protocolos.place(x=10, y=largura_janela - 23)

# Inicia a interface
janela.mainloop()

import matplotlib.pyplot as plt

# Leitura do CSV com as notícias dos últimos 5 dias
df = pd.read_csv('noticias_meio_ambiente_5_dias.csv')

# Pré-processar e vetorizar as notícias
df['noticia_processada'] = df['noticia'].apply(treinamento.preprocessar_texto)
X_noticias = treinamento.vectorizer.transform(df['noticia_processada'])

# Classificar com base nos modelos
df['classificacao'] = treinamento.model_mlp.predict(X_noticias)
df['tipo'] = treinamento.model_tipo.predict(X_noticias)

# Converte a coluna data e remove valores nulos
df['data'] = pd.to_datetime(df['data'], errors='coerce')
df = df.dropna(subset=['data'])

# Filtra os últimos 5 dias de notícias
ultimos_5_dias = sorted(df['data'].dt.date.unique())[-5:]  # últimos 5 dias com notícia
df_filtrado = df[df['data'].dt.date.isin(ultimos_5_dias)]

# Agrupa por dia e classificacao
agrupado = df_filtrado.groupby(df_filtrado['data'].dt.date)['classificacao'].value_counts().unstack(fill_value=0)
agrupado = agrupado.reindex(ultimos_5_dias)

# Separa boas e ruins
boas = agrupado.get(1, pd.Series([0]*5, index=ultimos_5_dias))
ruins = agrupado.get(0, pd.Series([0]*5, index=ultimos_5_dias))

# Define os rótulos para o eixo X
dias_str = [data.strftime("%m/%d") for data in ultimos_5_dias]
x = np.arange(len(dias_str))
largura = 0.35

# Cria o gráfico de barras por dia
plt.figure(figsize=(10, 5))
plt.bar(x - largura/2, boas, width=largura, label='Boas', color='green')
plt.bar(x + largura/2, ruins, width=largura, label='Ruins', color='red')
plt.xlabel("Dias")
plt.ylabel("Quantidade de Notícias")
plt.title("Notícias por Dia e Classificação")
plt.xticks(x, dias_str)
plt.legend()
plt.tight_layout()
plt.show()

# Define as categorias que existem no dataset para classificação
categorias_desejadas = [
    'Desmatamento e uso da terra', 'Poluição e resíduos', 'Clima e fenômenos extremos', 'Fauna e flora',
    'Energia e emissões', 'Políticas e ações ambientais', 'Conservação'
]

# Conta quantas vezes cada tipo apareceu
categorias_contagem = df['tipo'].value_counts()

# Garante que todas as categorias fiquem na ordem definida, independente de terem 0 notícias relacionadas
categorias_ordenadas = pd.Series({cat: categorias_contagem.get(cat, 0) for cat in categorias_desejadas})

# Gráfico de barras por tipo de noticia
plt.figure(figsize=(12, 6))
plt.bar(categorias_ordenadas.index, categorias_ordenadas.values, color='deepskyblue')
plt.xticks(rotation=45, ha='right')
plt.ylabel("Quantidade de Notícias")
plt.title("Quantidade de Notícias por Categoria (1 a 5 de maio)")
plt.tight_layout()
plt.show()