const fs=require('fs').promises

setTimeout(()=>{
	fs.unlink('./abcdefg.js')
	
},1000)