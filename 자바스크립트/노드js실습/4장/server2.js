const http=require('http')
const fs=require('fs').promises

http.createServer(async(req,res)=>{
	try{
		const data=await fs.readFile('./sserver2.html')
		res.writeHead(200,{'content-Type':'text/html;charset=utf-8'})
		res.end(data)
	}catch(err){
		console.error(err)
		res.writeHead(500,{'content-Type':'text/html;charset=utf-8'})
		res.end(err.message)		
	}
	
})
	.listen(8081,()=>{
		console.log('8081open')
	})