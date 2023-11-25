import { cilDelete, cilPlus, cilTrash } from '@coreui/icons'
import CIcon from '@coreui/icons-react'
import { CButton, CCallout, CFormInput, CFormSelect, CFormTextarea, CInputGroup } from '@coreui/react'
import React, { forwardRef, useEffect, useImperativeHandle, useRef, useState } from 'react'
import { makeRequest } from 'src/Api'

export const getArticles = () => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: 'articles',
            requestType: 'GET',
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => {
                reject(error)
            }
        })
    })
}

export const getUnites = () => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: 'unites',
            requestType: 'GET',
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => {
                reject(error)
            }
        })
    })
}

const SelectionArticle = forwardRef((props, ref) => {
    // Add and remove functions
    const { onRemove, index } = props

    // Selections articles
    const [ articlesOptions, setArticlesOptions ] = useState([])
    useEffect(() => {
        getArticles().then((articles) => {
            articles.forEach((article) => {
                setArticlesOptions(prev => ([...prev, { label: article.nom, value: article.idArticle }]))
            })
        })
    }, [])

    // Selections unites
    const [ unitesOptions, setUniteOptions ] = useState([])
    useEffect(() => {
        getUnites().then((unites) => {
            unites.forEach((unite) => {
                setUniteOptions(prev => ([...prev, { label: unite.nom, value: unite.idUnite }]))
            })
        })
    }, [])

    // Refs to inputs
    const articleRef = useRef(null)
    const quantiteRef = useRef(null)
    const uniteRef = useRef(null)
    const descriptionRef = useRef(null)

    useImperativeHandle(ref, () => ({
        getSelectedValues: () => {
            return {
                article: articleRef.current.value,
                quantite: quantiteRef.current.value,
                unite: uniteRef.current.value,
                description: descriptionRef.current.value,
            };
        },
    }));

  return (
    <CCallout className='mt-0 mb-0'>
        <div className='d-flex flex-column gap-2'>
            <div className='d-flex gap-2'>
                <div style={{ width: '65%' }}>
                    <CFormSelect floatingLabel='Article' ref={articleRef} options={['Articles', ...articlesOptions]} />
                </div>
                
                <CInputGroup style={{ width: '35%' }}>
                    <CFormInput type='number' ref={quantiteRef} step={0.01} floatingLabel='Quantite' />

                    <CFormSelect floatingLabel='Unite' ref={uniteRef} options={unitesOptions} />
                </CInputGroup>

                <CButton onClick={(e) => { onRemove(e, index) }} ><CIcon icon={cilTrash} /></CButton>
            </div>

            <CFormTextarea ref={descriptionRef} floatingLabel='Descrition besoin' rows={20} />
        </div>
    </CCallout>
  )
});
SelectionArticle.displayName = 'SelectionArticle'

export default SelectionArticle