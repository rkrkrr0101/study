const timeout= setTimeout(()=>{
	console.log('1.5')
},1500)
const interval= setInterval(()=>{
	console.log('1')
},1000)
const timeout2= setTimeout(()=>{
	console.log('x')
},3000)
setTimeout(()=>{
	clearTimeout(timeout2)
	clearInterval(interval)
	console.log('종료')
	
},2500)
const immediate= setImmediate(()=>{
	console.log('즉시실행')
})
const immediate2= setImmediate(()=>{
	console.log('실행x')
})
clearImmediate(immediate2)