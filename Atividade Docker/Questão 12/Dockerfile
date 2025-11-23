FROM python:3.9-slim

# Diretório
WORKDIR /app

# Instala a biblioteca 
RUN pip install flask

# Copia o arquivo
COPY app.py .

# Informa a porta 
EXPOSE 5000

# Executa 
CMD ["python", "app.py"]