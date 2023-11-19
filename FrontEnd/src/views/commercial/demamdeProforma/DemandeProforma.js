import { CModal, CModalBody, CModalHeader, CModalTitle } from '@coreui/react';
import React, { forwardRef, useImperativeHandle, useState } from 'react'

const DemandeProformaBesoin = forwardRef((props, ref) => {
    useImperativeHandle(ref, () => ({
        showModal: () => {
            setVisibilityDemandeModal(true)
        },
        hideModal: () => {
            setVisibilityDemandeModal(false)
        }
    }))

    // Modal visibility
    const [ visibilityDemandeModal, setVisibilityDemandeModal ] = useState(false)

    return (
        <CModal
            visible={visibilityDemandeModal}
            onClose={() => {
                setVisibilityDemandeModal(false)
            }}
            size='lg'
        >
            <CModalHeader>
                <CModalTitle>
                    Demande de proforma
                </CModalTitle>
            </CModalHeader>

            <CModalBody>

            </CModalBody>
        </CModal>
    );
});

DemandeProformaBesoin.displayName = "DemandeProformaBesoin"
export default DemandeProformaBesoin