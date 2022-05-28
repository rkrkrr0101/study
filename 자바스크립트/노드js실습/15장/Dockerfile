FROM node:12-alpine
WORKDIR /usr/src/app
COPY package*.json ./usr/src/app

COPY . /usr/src/app
RUN npm install

RUN npx sequelize db:create
EXPOSE 3000
CMD ["npm","start"]