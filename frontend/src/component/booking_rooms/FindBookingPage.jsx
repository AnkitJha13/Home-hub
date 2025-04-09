import React, { useState } from 'react';
import ApiService from '../../service/ApiService';

const FindBookingPage = () => {
    const [confirmationCode, setConfirmationCode] = useState('');
    const [bookingDetails, setBookingDetails] = useState(null);
    const [error, setError] = useState(null);

    const handleSearch = async () => {
        if (!confirmationCode.trim()) {
            setError("Please enter a booking confirmation code");
            setTimeout(() => setError(null), 5000);
            return;
        }
        try {
            const response = await ApiService.getBookingByConfirmationCode(confirmationCode);
            setBookingDetails(response.booking);
            setError(null);
        } catch (error) {
            setError(error.response?.data?.message || error.message);
            setTimeout(() => setError(null), 5000);
        }
    };

    return (
        <div style={styles.page}>
            <h2 style={styles.title}>Find Booking Details</h2>

            <div style={styles.searchContainer}>
                <input
                    type="text"
                    placeholder="Enter your booking confirmation code"
                    value={confirmationCode}
                    onChange={(e) => setConfirmationCode(e.target.value)}
                    style={styles.input}
                />
                <button onClick={handleSearch} style={styles.button}>Find</button>
            </div>

            {error && <p style={styles.error}>{error}</p>}

            {bookingDetails && (
                <div style={styles.detailsContainer}>
                    <h3 style={styles.sectionTitle}>Booking Details</h3>
                    <p>Confirmation Code: <strong>{bookingDetails.bookingConfirmationCode}</strong></p>
                    <p>Check-in Date: {bookingDetails.checkInDate}</p>
                    <p>Check-out Date: {bookingDetails.checkOutDate}</p>
                    <p>Num Of Adults: {bookingDetails.numOfAdults}</p>
                    <p>Num Of Children: {bookingDetails.numOfChildren}</p>

                    <hr style={styles.divider} />

                    <h3 style={styles.sectionTitle}>Booker Details</h3>
                    <p>Name: {bookingDetails.user.name}</p>
                    <p>Email: {bookingDetails.user.email}</p>
                    <p>Phone Number: {bookingDetails.user.phoneNumber}</p>

                    <hr style={styles.divider} />

                    <h3 style={styles.sectionTitle}>Room Details</h3>
                    <p>Room Type: {bookingDetails.room.roomType}</p>
                    <img
                        src={bookingDetails.room.roomPhotoUrl}
                        alt="Room"
                        style={styles.roomImage}
                    />
                </div>
            )}
        </div>
    );
};

const styles = {
    page: {
        padding: '2rem',
        maxWidth: '800px',
        margin: '0 auto',
        fontFamily: 'Arial, sans-serif'
    },
    title: {
        textAlign: 'center',
        color: '#006666',
        marginBottom: '1.5rem'
    },
    searchContainer: {
        display: 'flex',
        justifyContent: 'center',
        marginBottom: '1.5rem',
        gap: '0.5rem'
    },
    input: {
        padding: '0.6rem',
        width: '300px',
        borderRadius: '5px',
        border: '1px solid #ccc',
        fontSize: '1rem'
    },
    button: {
        padding: '0.6rem 1.2rem',
        backgroundColor: '#008080',
        color: '#fff',
        border: 'none',
        borderRadius: '5px',
        cursor: 'pointer'
    },
    error: {
        color: 'red',
        textAlign: 'center',
        marginBottom: '1rem'
    },
    detailsContainer: {
        marginTop: '2rem',
        backgroundColor: '#f9f9f9',
        padding: '1.5rem',
        borderRadius: '8px',
        boxShadow: '0 0 10px rgba(0,0,0,0.1)'
    },
    sectionTitle: {
        marginTop: '1rem',
        marginBottom: '0.5rem',
        color: '#333'
    },
    divider: {
        margin: '1.5rem 0',
        border: 'none',
        borderTop: '1px solid #ddd'
    },
    roomImage: {
        width: '100%',
        maxWidth: '400px',
        height: 'auto',
        marginTop: '0.5rem',
        borderRadius: '8px',
        border: '1px solid #ccc'
    }
};

export default FindBookingPage;
