import { finishLoading, startLoading } from "../module/loading"

export default function createRequestThunk(type,request) {
const SUCCESS=`${type}_SUCCESS`
const FAILURE=`${type}_FAILURE`
return (params,a) =>async dispatch=>{
    dispatch({type})
    dispatch(startLoading(type))
    try{
        console.log(a)
        const response= await request(params)
        dispatch({
            type:SUCCESS,
            payload:response.data
        })
        dispatch(finishLoading(type))
    }catch(e){
        dispatch({
            type:FAILURE,
            payload:e,
            error:true
        })
        dispatch(finishLoading(type))
        throw e;
    }
}
    
}