import Joi from 'joi';
import User from '../../models/user';

export const register = async (ctx) => {
    const schema = Joi.object().keys({
        username: Joi.string().alphanum().min(3).max(20).required(),
        password: Joi.string().required(),
    });

    const result = schema.validate(ctx.request.body);
    if (result.error) {
        ctx.status = 400;
        ctx.body = result.error;
        return;
    }
    const { username, password } = ctx.request.body;
    try {
        const exists = await User.findByUserName(username);
        if (exists) {
            ctx.status = 409;
            return;
        }
        const user = new User({
            username,
        });

        await user.setPassword(password);
        await user.save();

        ctx.body = user.seriallize();

        const token = user.generateToken();
        ctx.cookies.set('access_token', token, {
            maxAge: 1000 * 60 * 60 * 24 * 7,
            httpOnly: true,
        });
    } catch (error) {
        ctx.throw(500, error);
    }
};

export const login = async (ctx) => {
    const { username, password } = ctx.request.body;

    if (!username || !password) {
        ctx.status = 401;
        return;
    }
    try {
        const user = await User.findByUserName(username);
        if (!user) {
            ctx.status = 401;
            return;
        }
        const vaild = await user.CheckPassword(password);
        if (!vaild) {
            ctx.status = 401;
            return;
        }
        ctx.body = user.seriallize();

        const token = user.generateToken();
        ctx.cookies.set('access_token', token, {
            maxAge: 1000 * 60 * 60 * 24 * 7,
            httpOnly: true,
        });
    } catch (e) {
        ctx.throw(500, e);
    }
};
export const check = async (ctx) => {
    const { user } = ctx.state;
    if (!user) {
        ctx.status = 401;
        return;
    }
    ctx.body = user;
};
export const logout = async (ctx) => {
    ctx.cookies.set('access_token');
    ctx.status = 204;
};
