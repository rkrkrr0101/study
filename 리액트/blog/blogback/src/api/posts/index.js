import Router from 'koa-router';
import * as postsCtrl from './posts.ctrl';
import checkLoggedIn from '../../lib/checkLoggedln';

const posts = new Router();

posts.get('/', postsCtrl.list);
posts.post('/', checkLoggedIn, postsCtrl.write);

const post = new Router();
post.get('/', postsCtrl.getPostById, postsCtrl.read);
post.delete('/', checkLoggedIn, postsCtrl.CheckOwnPost, postsCtrl.remove);
post.patch('/', checkLoggedIn, postsCtrl.CheckOwnPost, postsCtrl.update);

posts.use('/:id', postsCtrl.getPostById, post.routes());
export default posts;
