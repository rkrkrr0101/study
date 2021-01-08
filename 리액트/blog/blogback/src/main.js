require('dotenv').config();
import Koa from 'koa';
import Router from 'koa-router';
import bodyParser from 'koa-bodyparser';
import mongoose from 'mongoose';
import createFakeData from './createFakeData';
import jwtMiddleWare from './lib/jwtMiddleWare';

const { PORT, MONGO_URI } = process.env;

mongoose
    .connect(MONGO_URI, { useNewUrlParser: true, useFindAndModify: false })
    .then(() => {
        console.log('connect');
    })
    .catch((e) => {
        console.error(e);
    });

import api from './api';

const app = new Koa();
const router = new Router();

router.use('/api', api.routes());

app.use(bodyParser());
app.use(jwtMiddleWare);

app.use(router.routes()).use(router.allowedMethods());

console.log(PORT);
const port = PORT || 4000;

app.listen(port, () => {
    console.log('listen to port %d', port);
});
