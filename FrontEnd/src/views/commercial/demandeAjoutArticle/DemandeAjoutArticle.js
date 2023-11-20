import { CButton, CForm, CFormInput, CModal, CModalBody, CModalFooter, CModalHeader, CModalTitle } from '@coreui/react'
import React, { forwardRef, useImperativeHandle, useRef, useState } from 'react'
import { makeRequest } from 'src/Api'
import { ajouterProforma } from '../ajoutProforma/AjoutProforma'

export const ajouterDemandeArticle = (demande) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: '/demandeajoutarticle',
            requestType: 'POST',
            values: demande,
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => [
                reject(error)
            ]
        })
    })
}

const DemandeAjoutArticle = forwardRef((props, ref) => {
    // Modal visibility
    const [ visibilityDemandeModal, setVisibilityDemandeModal ] = useState(false)

    useImperativeHandle(ref, () => ({
        showModal: () => {
            setVisibilityDemandeModal(true)
        },
        closeModal: () => {
            setVisibilityDemandeModal(false)
        }
    }))

    // Handle submit demande
    const nomArticleRef = useRef()
    const handleSubmitDemandeArticle = (e) => {
        e.preventDefault()

        let data = {
            nomArticle: nomArticleRef.current.value
        }

        ajouterDemandeArticle(data).then((data) => {
            setVisibilityDemandeModal(false)
        })
    }

    return (
        <CModal
            visible={visibilityDemandeModal}
            onClose={ () => {
                setVisibilityDemandeModal(false)
            } }
        >
            <CForm
                onSubmit={handleSubmitDemandeArticle}
            >
                <CModalHeader>
                    <CModalTitle>
                        <h5>Faire une demande ajout article</h5>
                    </CModalTitle>
                </CModalHeader>

                <CModalBody>
                    <CFormInput ref={nomArticleRef} floatingLabel="Nom article" />
                </CModalBody>

                <CModalFooter>
                    <CButton type='submit'>Faire une demande</CButton>
                </CModalFooter>
            </CForm>
        </CModal>
    )
})

DemandeAjoutArticle.displayName = "DemandeAjoutArticle"
export default DemandeAjoutArticle