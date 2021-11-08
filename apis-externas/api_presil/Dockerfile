FROM node:alpine

WORKDIR /usr/app

COPY package*.json /usr/app/
RUN npm i

COPY . .

EXPOSE 3333

CMD ["npm", "start"]