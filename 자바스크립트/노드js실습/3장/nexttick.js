setImmediate(()=>{
	console.log('imm')
	
})
process.nextTick(()=>{
	console.log('nexttick')
	
})
setTimeout(()=>{
	console.log('to')
},0)
Promise.resolve().then(()=>console.log('pro'))