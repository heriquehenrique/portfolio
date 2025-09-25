import pandas as pd
import numpy as np
import nltk
import string
from sklearn.model_selection import train_test_split
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.neural_network import MLPClassifier
import random

# Fixando as sementes para garantir que os resultados sejam sempre os mesmos (reprodutibilidade)
SEED = 42
random.seed(SEED)
np.random.seed(SEED)

# Baixando os recursos do NLTK para tokenização e remoção de stopwords
nltk.download('punkt')
nltk.download('stopwords')
nltk.download('rslp')

# Definindo a lista de stopwords e o stemmer para português
stopwords = nltk.corpus.stopwords.words('portuguese')
stemmer = nltk.stem.RSLPStemmer()

# Função para pré-processar o texto (limpeza e simplificação)
def preprocessar_texto(texto):
    texto = texto.lower() # Converte tudo para minúsculo
    tokens = nltk.word_tokenize(texto, language='portuguese') # Divide o texto em palavras
    tokens = [t for t in tokens if t not in string.punctuation and t not in stopwords] # Remove pontuação e stopwords
    tokens = [stemmer.stem(t) for t in tokens] # Aplica o stemmer para reduzir as palavras à sua raiz
    return " ".join(tokens) # Junta tudo de volta em um único texto processado

# Relatório de Desempenho
from sklearn.metrics import classification_report, confusion_matrix
from sklearn import metrics
from sklearn.metrics import accuracy_score
from time import time

def mostrar_desempenho(x_train, y_train, x_test, y_test, model, name):
    # Treinando modelo
    inicio = time()
    model.fit(x_train, y_train)
    fim = time()
    tempo_treinamento = (fim - inicio)*1000

    # Prevendo dados
    inicio = time()
    y_predicted = model.predict(x_test)
    fim = time()
    tempo_previsao = (fim - inicio)*1000

    # Resultados
    print('\nRelatório Utilizando Algoritmo', name)
    print('Mostrando Matriz de Confusão:')
    print(confusion_matrix(y_test, y_predicted))
    print('\nMostrando Relatório de Classificação:')
    print(metrics.classification_report(y_test, y_predicted))
    accuracy = accuracy_score(y_test, y_predicted)
    print('Accuracy:', accuracy)
    relatorio = metrics.classification_report(y_test, y_predicted, output_dict=True)
    print('Precision:', relatorio['macro avg']['precision'])
    print('Tempo de treinamento (ms):',tempo_treinamento)
    print('Tempo de previsão (ms):',tempo_previsao)
    return accuracy, tempo_treinamento, tempo_previsao

# Leitura do CSV de notícias ambientais
df = pd.read_csv("noticias_meio_ambiente.csv", sep=",", encoding="utf-8")
# Aplicando o pré-processamento nas notícias
df['noticia'] = df['noticia'].apply(preprocessar_texto)

# Separando os dados (x (feature) para notícias e y (label) para saída esperada = classificação
x_data = df['noticia']
y_data = df['classificacao']

# Divisão de dados para treino (70%) e para teste (30%)
x_train, x_test, y_train, y_test = train_test_split(x_data, y_data, test_size=0.3, random_state=42)

# Criando o vetorizador TF-IDF com unigramas e bigramas (palavras individuais e pares de palavras)
vectorizer = TfidfVectorizer(ngram_range=(1,2))

# Transformando os dados de treino e de teste em vetores para o treinamento
x_train_vect = vectorizer.fit_transform(x_train)
x_test_vect = vectorizer.transform(x_test)

# Criando a rede neural para classificação das notícias
model_mlp = MLPClassifier(hidden_layer_sizes=(50,50,50), max_iter=10000, random_state=SEED)
model_mlp.fit(x_train_vect, y_train)

# Rede neural para tipos de notícias
model_tipo = MLPClassifier(hidden_layer_sizes=(50,50,50), max_iter=10000, random_state=SEED)
model_tipo.fit(x_train_vect, df.loc[x_train.index, 'tipo'])

# Função para classificar uma notícia inserida pelo usuário
def classificar_noticia(texto):
    proc = preprocessar_texto(texto)
    vetorizado = vectorizer.transform([proc])
    probas = model_mlp.predict_proba(vetorizado)
    pred = model_mlp.predict(vetorizado)
    confianca = np.max(probas)
    tipo_pred = model_tipo.predict(vetorizado)[0]
    return pred[0], tipo_pred, confianca