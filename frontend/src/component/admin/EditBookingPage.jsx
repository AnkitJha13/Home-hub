import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import ApiService from '../../service/ApiService';

const EditBookingPage = () => {
    const navigate = useNavigate();
    const { bookingCode } = useParams();
    const [bookingDetails, setBookingDetails] = useState(null);
    const [error, setError] = useState(null);
    const [success, setSuccessMessage] = useState(null);
    const [buttonStyle, setButtonStyle] = useState({
        padding: '10px 20px',
        backgroundColor: '#d32f2f', // More noticeable red
        color: 'white',
        border: 'none',
        borderRadius: '6px',
        cursor: 'pointer',
        fontWeight: 'bold',
        boxShadow: '0 2px 5px rgba(0,0,0,0.15)',
        outline: 'none',
        transition: 'all 0.3s ease'
    });

    useEffect(() => {
        const fetchBookingDetails = async () => {
            try {
                const response = await ApiService.getBookingByConfirmationCode(bookingCode);
                setBookingDetails(response.booking);
            } catch (error) {
                setError(error.message);
            }
        };

        fetchBookingDetails();
    }, [bookingCode]);

    const acheiveBooking = async (bookingId) => {
        if (!window.confirm('Are you sure you want to delete this booking?')) return;

        try {
            const response = await ApiService.cancelBooking(bookingId);
            if (response.statusCode === 200) {
                setSuccessMessage("The booking was successfully deleted");
                setTimeout(() => {
                    setSuccessMessage('');
                    navigate('/admin/manage-bookings');
                }, 3000);
            }
        } catch (error) {
            setError(error.response?.data?.message || error.message);
            setTimeout(() => setError(''), 5000);
        }
    };

    return (
        <div style={{ padding: '40px 20px', maxWidth: '900px', margin: 'auto' }}>
            <h2 style={{ textAlign: 'center', color: '#00695c', marginBottom: '30px' }}>Booking Detail</h2>

            {error && <p style={{ color: 'red', backgroundColor: '#ffebee', padding: '10px', borderRadius: '6px' }}>{error}</p>}
            {success && <p style={{ color: 'green', backgroundColor: '#e8f5e9', padding: '10px', borderRadius: '6px' }}>{success}</p>}

            {bookingDetails && (
                <div style={{
                    backgroundColor: '#ffffff',
                    borderRadius: '10px',
                    boxShadow: '0 2px 10px rgba(0,0,0,0.1)',
                    padding: '25px',
                    lineHeight: '1.8'
                }}>
                    <section style={{ marginBottom: '25px' }}>
                        <h3 style={{ borderBottom: '2px solid #ddd', paddingBottom: '5px' }}>Booking Details</h3>
                        <p><strong>Confirmation Code:</strong> {bookingDetails.bookingConfirmationCode}</p>
                        <p><strong>Check-in Date:</strong> {bookingDetails.checkInDate}</p>
                        <p><strong>Check-out Date:</strong> {bookingDetails.checkOutDate}</p>
                        <p><strong>Num Of Adults:</strong> {bookingDetails.numOfAdults}</p>
                        <p><strong>Num Of Children:</strong> {bookingDetails.numOfChildren}</p>
                    </section>

                    <section style={{ marginBottom: '25px' }}>
                        <h3 style={{ borderBottom: '2px solid #ddd', paddingBottom: '5px' }}>Booker Details</h3>
                        <p><strong>Name:</strong> {bookingDetails.user.name}</p>
                        <p><strong>Email:</strong> {bookingDetails.user.email}</p>
                        <p><strong>Phone Number:</strong> {bookingDetails.user.phoneNumber}</p>
                    </section>

                    <section style={{ marginBottom: '25px' }}>
                        <h3 style={{ borderBottom: '2px solid #ddd', paddingBottom: '5px' }}>Room Details</h3>
                        <p><strong>Room Type:</strong> {bookingDetails.room.roomType}</p>
                        <p><strong>Room Price:</strong> ₹{bookingDetails.room.roomPrice}</p>
                        <p><strong>Description:</strong> {bookingDetails.room.roomDescription}</p>
                        <img
                            src={bookingDetails.room.roomPhotoUrl}
                            alt="Room"
                            style={{
                                width: '100%',
                                height: '300px',
                                objectFit: 'cover',
                                borderRadius: '8px',
                                marginTop: '10px'
                            }}
                        />
                    </section>

                    <button
                        onClick={() => acheiveBooking(bookingDetails.id)}
                        style={buttonStyle}
                        onMouseEnter={() => setButtonStyle(prev => ({
                            ...prev,
                            backgroundColor: '#b71c1c' // Darker red on hover
                        }))}
                        onMouseLeave={() => setButtonStyle(prev => ({
                            ...prev,
                            backgroundColor: '#d32f2f' // Reset to default red
                        }))}
                        onMouseDown={() => setButtonStyle(prev => ({
                            ...prev,
                            backgroundColor: '#880e4f', // Even darker on click
                            transform: 'scale(0.98)'
                        }))}
                        onMouseUp={() => setButtonStyle(prev => ({
                            ...prev,
                            backgroundColor: '#b71c1c',
                            transform: 'scale(1)'
                        }))}
                        onFocus={() => setButtonStyle(prev => ({
                            ...prev,
                            boxShadow: '0 0 0 3px rgba(211,47,47,0.4)'
                        }))}
                        onBlur={() => setButtonStyle(prev => ({
                            ...prev,
                            boxShadow: '0 2px 5px rgba(0,0,0,0.15)'
                        }))}
                    >
                        Delete Booking
                    </button>
                </div>
            )}
        </div>
    );
};

export default EditBookingPage;
