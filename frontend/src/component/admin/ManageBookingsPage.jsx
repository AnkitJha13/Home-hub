import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import ApiService from '../../service/ApiService';
import Pagination from '../common/Pagination';

const ManageBookingsPage = () => {
    const [bookings, setBookings] = useState([]);
    const [filteredBookings, setFilteredBookings] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [currentPage, setCurrentPage] = useState(1);
    const [bookingsPerPage] = useState(6);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchBookings = async () => {
            try {
                const response = await ApiService.getAllBookings();
                const allBookings = response.bookingList;
                setBookings(allBookings);
                setFilteredBookings(allBookings);
            } catch (error) {
                console.error('Error fetching bookings:', error.message);
            }
        };

        fetchBookings();
    }, []);

    useEffect(() => {
        filterBookings(searchTerm);
    }, [searchTerm, bookings]);

    const filterBookings = (term) => {
        if (term === '') {
            setFilteredBookings(bookings);
        } else {
            const filtered = bookings.filter((booking) =>
                booking.bookingConfirmationCode &&
                booking.bookingConfirmationCode.toLowerCase().includes(term.toLowerCase())
            );
            setFilteredBookings(filtered);
        }
        setCurrentPage(1);
    };

    const handleSearchChange = (e) => {
        setSearchTerm(e.target.value);
    };

    const indexOfLastBooking = currentPage * bookingsPerPage;
    const indexOfFirstBooking = indexOfLastBooking - bookingsPerPage;
    const currentBookings = filteredBookings.slice(indexOfFirstBooking, indexOfLastBooking);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <div style={{ padding: '30px', maxWidth: '1200px', margin: 'auto' }}>
            <h2 style={{ textAlign: 'center', color: '#006666', marginBottom: '30px' }}>All Bookings</h2>

            <div style={{ textAlign: 'center', marginBottom: '20px' }}>
                <label style={{ fontWeight: 'bold', marginRight: '10px' }}>Filter by Booking Number:</label>
                <input
                    type="text"
                    value={searchTerm}
                    onChange={handleSearchChange}
                    placeholder="Enter booking number"
                    style={{
                        padding: '8px 12px',
                        width: '250px',
                        borderRadius: '8px',
                        border: '1px solid #ccc',
                        fontSize: '14px'
                    }}
                />
            </div>

            <div style={{
                display: 'grid',
                gridTemplateColumns: 'repeat(auto-fill, minmax(300px, 1fr))',
                gap: '20px',
                marginBottom: '30px'
            }}>
                {currentBookings.map((booking) => (
                    <div
                        key={booking.id}
                        onClick={() => navigate(`/admin/edit-booking/${booking.bookingConfirmationCode}`)}
                        style={{
                            backgroundColor: '#f9f9f9',
                            border: '1px solid #ddd',
                            borderRadius: '12px',
                            padding: '20px',
                            boxShadow: '0 2px 6px rgba(0, 0, 0, 0.1)',
                            cursor: 'pointer',
                            transition: 'transform 0.2s, box-shadow 0.2s',
                        }}
                        onMouseOver={(e) => {
                            e.currentTarget.style.transform = 'scale(1.02)';
                            e.currentTarget.style.boxShadow = '0 4px 12px rgba(0, 0, 0, 0.15)';
                        }}
                        onMouseOut={(e) => {
                            e.currentTarget.style.transform = 'scale(1)';
                            e.currentTarget.style.boxShadow = '0 2px 6px rgba(0, 0, 0, 0.1)';
                        }}
                    >
                        <p><strong>Booking Code:</strong> {booking.bookingConfirmationCode}</p>
                        <p><strong>Check In Date:</strong> {booking.checkInDate}</p>
                        <p><strong>Check Out Date:</strong> {booking.checkOutDate}</p>
                        <p><strong>Total Guests:</strong> {booking.totalNumOfGuest}</p>
                        <button
                            onClick={(e) => {
                                e.stopPropagation(); // prevent card click from firing
                                navigate(`/admin/edit-booking/${booking.bookingConfirmationCode}`);
                            }}
                            style={{
                                marginTop: '10px',
                                padding: '8px 16px',
                                backgroundColor: '#00796b',
                                color: '#fff',
                                border: 'none',
                                borderRadius: '6px',
                                cursor: 'pointer',
                                fontWeight: 'bold',
                                transition: 'background-color 0.3s'
                            }}
                            onMouseOver={(e) => e.currentTarget.style.backgroundColor = '#004d40'}
                            onMouseOut={(e) => e.currentTarget.style.backgroundColor = '#00796b'}
                        >
                            Manage Booking
                        </button>
                    </div>
                ))}
            </div>

            <Pagination
                roomsPerPage={bookingsPerPage}
                totalRooms={filteredBookings.length}
                currentPage={currentPage}
                paginate={paginate}
            />
        </div>
    );
};

export default ManageBookingsPage;
