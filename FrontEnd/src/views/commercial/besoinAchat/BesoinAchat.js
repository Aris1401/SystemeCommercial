import { CCard, CCardBody, CCardTitle, CModal, CModalBody, CModalTitle } from '@coreui/react';
import React, { useState } from 'react'

const getValidBesoinsAchat = () => {
    return [];
}

const BesoinAchat = () => {
    // Visibilite de modal d'ajout de besoin
    const [ visibilityAjoutModal, setVisibilityAjoutModal ] = useState(false);

    return (
        <>
            {/* Modal d'ajout de besoin */}
            <CModal
                visible = {visibilityAjoutModal}
                onClose= { () => {
                    setVisibilityAjoutModal(false);
                } }
            >
                <CModalTitle>
                    Ajout de besoin achat
                </CModalTitle>

                <CModalBody>

                </CModalBody>
            </CModal>

            {/* Liste et bouton ajout de besoins achat */}
            <CCard>
                <CCardBody>
                    <CCardTitle>
                        <h3>
                            Besoins en cours
                        </h3>
                    </CCardTitle>
                </CCardBody>
            </CCard>
        </>
    );
}

export default BesoinAchat