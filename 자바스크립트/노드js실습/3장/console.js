const str='abc'
const num=1
const bool=true
const obj={
	outside:{
		inside:{
			key:'value'
		}
	}
}
console.time('전체시간')
console.log('abcd')
console.log(str,num,bool)
console.error('에러')
console.table([{name:'zero',birth:1994},{name:'hero',birth:19934}])
console.dir(obj,{colors:false,depth:2})
console.dir(obj,{colors:true,depth:1})
console.time('시간')
for (let i=0;i<100000;i++){}
console.timeEnd('시간')

function b(){
	console.trace('에러위치')
}
function a(){
	b()
}
a()
console.timeEnd('전체시간')