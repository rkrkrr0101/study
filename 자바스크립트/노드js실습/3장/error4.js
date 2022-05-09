process.on('uncaughtException',(err)=>{
	console.error('abcd',err)
	
})
setInterval(()=>{
	throw new Error('서버고장')
},1000)

setTimeout(()=>{
	console.log('실행')
},2000)