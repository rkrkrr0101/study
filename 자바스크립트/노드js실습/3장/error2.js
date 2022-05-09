const fs=require('fs')
setTimeout(()=>{
	fs.unlink('./abcdefg.js',(err)=>{
		if (err){
			console.error(err)
		}
	})
	
},1000)