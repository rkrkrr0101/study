const http=require('http')
const fs=require('fs').promises

const users={}

http.createServer(async(req,res)=>{
	try{
		console.log(req.method,req.url)
		if (req.method==='GET'){
			if (req.url==='/'){
				const data= await fs.readFile('./restFront.html')
				res.writeHead(200,{'content-Type':'text/html;charset=utf-8'})
				return res.end(data)
			}else if(req.url==='/about'){
				const data= await fs.readFile('./about.html')
				res.writeHead(200,{'content-Type':'text/html;charset=utf-8'})
				return res.end(data)
			}else if(req.url==='/users'){
				res.writeHead(200,{'content-Type':'text/plain;charset=utf-8'})
				return res.end(JSON.stringify(users))
				
			
			}else{
				try{
					const data= await fs.readFile(`.${req.url}`)
					//console.log(req.url)
					return res.end(data)

				}catch(err){
				//	res.writeHead(404)
				//	return res.end('NfOT FOUND')
				}
			}
			
		}else if (req.method==='POST'){
			if (req.url==='/user'){
				let body=''
				req.on('data',(data)=>{
					body+=data
				})
				return req.on('end',()=>{
					console.log('post본문:',body)
					const {name}=JSON.parse(body)
					const id=Date.now()
					users[id]=name
					res.writeHead(201)
					res.end('등록성공')
				})
			}
			
		}else if (req.method==='PUT'){
			if (req.url.startsWith('/user/')){
				const key=req.url.split('/')[2]
				let body=''
				req.on('data',(data)=>{
					body+=data
				})
				return req.on('end',()=>{
					console.log('put본문:',body)
					users[key]=JSON.parse(body).name
					return res.end(JSON.stringify(users))


				})
			}
			
		}else if (req.method==='DELETE'){
			if (req.url.startsWith('/user/')){
				const key=req.url.split('/')[2]
				
				delete users[key]
				return res.end('ok')

			}
			res.writeHead(404)
			return res.end('NOT FOUND')
			
			
		}
	}catch(err){
		console.error(err)
		res.writeHead(500,{'content-Type':'text/html;charset=utf-8'})
		res.end(err.message)		
	}
	
})
	.listen(8080,()=>{
		console.log('8080open')
	})