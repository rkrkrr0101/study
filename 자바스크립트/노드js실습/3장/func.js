const {odd,even}=require('./var')

const checkOddorEven=(num)=>{
	if (num%2){
		return odd
	} else{
		return even
	}
	
}
module.exports=checkOddorEven