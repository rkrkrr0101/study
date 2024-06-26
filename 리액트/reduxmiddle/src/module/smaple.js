import {createAction, handleAction, handleActions} from 'redux-actions'
import * as api from '../lib/api'
import createRequestThunk from '../lib/createRequestThunk'
import {call,put,takeLatest} from 'redux-saga/effects'
import { finishLoading, startLoading } from './loading'
import createRequestSaga from '../lib/createRequestSaga'

const GET_POST='sample/GET_POST'
const GET_POST_SUCCESS='sample/GET_POST_SUCCESS'
const GET_POST_FAILURE='sample/GET_POST_FAILURE'

const GET_USERS='sample/GET_USERS'
const GET_USERS_SUCCESS='sample/GET_USERS_SUCCESS'
const GET_USERS_FAILURE='sample/GET_USERS_FAILURE'

//export const getPost=createRequestThunk(GET_POST,api.getPost)

//export const getUsers=createRequestThunk(GET_USERS,api.getUsers)

export const getPost=createAction(GET_POST,id=>id)
export const getUsers=createAction(GET_USERS)

const getPostSaga=createRequestSaga(GET_POST,api.getPost)

const getUsersSaga=createRequestSaga(GET_USERS,api.getUsers)
 

export function* sampleSaga(){
    yield takeLatest(GET_POST,getPostSaga)
    yield takeLatest(GET_USERS,getUsersSaga)
}

const initstate={

    post:null,
    users:null

}

const sample=handleActions(
    {

        [GET_POST_SUCCESS]:(state,action)=>({
            ...state,

            post:action.payload
        }),
        [GET_POST_FAILURE]:(state,action)=>({
            ...state,

        }),
        [GET_USERS_SUCCESS]:(state,action)=>({
            ...state,
  
            
            users:action.payload
        }),
        [GET_USERS_FAILURE]:(state,action)=>({
            ...state,
        })
                
    },initstate)

export default sample
