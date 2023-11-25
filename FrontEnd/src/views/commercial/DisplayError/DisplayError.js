import { CTableBody, CToast, CToastBody, CToastHeader } from '@coreui/react'
import React, { forwardRef, useImperativeHandle, useState } from 'react'

const DisplayError = forwardRef((props, ref) => {

    // Displaying error
    const [ displayError, setDisplayError ] = useState(false);
    const [ errorMessage, setErrorMessage ] = useState("");

    useImperativeHandle(ref, () => ({
        showError: (message) => {
            setDisplayError(true)
            setErrorMessage(message)
        }
    }))

    return (
        <CToast visible={displayError} animation={true}  onClose={(index) => {
            setDisplayError(false)
        }} color='danger' style={{ position: 'fixed', right: '2%', top: '2%', zIndex: 9000 }}>
            <CToastHeader closeButton>
                <div className="fw-bold me-auto">Message</div>
            </CToastHeader>

            <CToastBody>
                <p className='p-2 m-0 text-white fw-bolder'>
                    { errorMessage }
                </p>
            </CToastBody>
        </CToast>
    )
})

DisplayError.displayName = "DisplayError"
export default DisplayError