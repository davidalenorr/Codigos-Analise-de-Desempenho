# Node.js
FROM node:18-alpine

# Diretorio
WORKDIR /app

# Instala o Git 
RUN apk add --no-cache git

# Clona o projeto do GitHub 
RUN git clone https://github.com/eMahtab/node-express-hello-world.git .

# Instala as dependências
RUN npm install

# Expõe a porta
EXPOSE 3000

# Executa
CMD ["npm", "start"]