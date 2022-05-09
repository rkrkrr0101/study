const util=require('util')
const crypto=require('crypto')

const rbp=util.promisify(crypto.randomBytes)
rbp(64)
	.then((buf)=>{
		console.log(buf.toString('base64'))
	})
	.catch((error)=>{
		console.error(error)
	})