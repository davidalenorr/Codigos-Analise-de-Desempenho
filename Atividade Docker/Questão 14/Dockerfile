# Ubuntu
FROM ubuntu:latest

# Diretorio
WORKDIR /app

# Instala o Python 3
RUN apt-get update && apt-get install -y python3

# Copia o arquivo
COPY process.py .

# Executa 
CMD ["python3", "process.py"]