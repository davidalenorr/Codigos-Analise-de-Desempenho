FROM python:3.9-slim

# Diretorio
WORKDIR /app

# Flask
RUN pip install flask

# Copia o arquivo
COPY app.py .

# Executa
CMD ["python", "app.py"]