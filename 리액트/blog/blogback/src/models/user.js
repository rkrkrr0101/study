import mongoose, { Schema } from 'mongoose';
import bcrypt from 'bcrypt';

const UserSchema = new Schema({
    username: String,
    hashedPassword: String,
});

UserSchema.methods.setPassword = async function (password) {
    const hash = await bcrypt.hash(password, 10);
    this.hashedPassword = hash;
};
UserSchema.methods.CheckPassword = async function (password) {
    const result = await bcrypt.compare(password, this.hashedPassword);
    return result;
};
UserSchema.statics.findByUserName = function (username) {
    return this.findOne({ username });
};

UserSchema.methods.seriallize = function () {
    const data = this.toJSON();
    delete data.hashedPassword;
    return data;
};

const user = mongoose.model('user', UserSchema);

export default user;
