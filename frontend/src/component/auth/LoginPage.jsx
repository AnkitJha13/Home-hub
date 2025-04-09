import React, { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import ApiService from "../../service/ApiService";

function LoginPage() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const location = useLocation();

    const from = location.state?.from?.pathname || '/home';

    useEffect(() => {
        const style = document.createElement('style');
        style.innerHTML = `
        @keyframes wave {
            0% { transform: rotate(0deg); }
            15% { transform: rotate(14deg); }
            30% { transform: rotate(-8deg); }
            45% { transform: rotate(14deg); }
            60% { transform: rotate(-4deg); }
            75% { transform: rotate(10deg); }
            100% { transform: rotate(0deg); }
        }
        .wave {
            display: inline-block;
            transform-origin: 70% 70%;
            animation: wave 2s infinite;
        }
        `;
        document.head.appendChild(style);
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!email || !password) {
            setError('Please fill in all fields.');
            setTimeout(() => setError(''), 5000);
            return;
        }

        try {
            const response = await ApiService.loginUser({ email, password });
            if (response.statusCode === 200) {
                localStorage.setItem('token', response.token);
                localStorage.setItem('role', response.role);
                navigate(from, { replace: true });
            }
        } catch (error) {
            setError(error.response?.data?.message || error.message);
            setTimeout(() => setError(''), 5000);
        }
    };

    const styles = {
        container: {
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'flex-start',
            minHeight: '100vh',
            backgroundColor: '#f0f4f8',
            paddingTop: '60px', // Push slightly up from bottom
        },
        box: {
            backgroundColor: '#fff',
            padding: '30px',
            borderRadius: '12px',
            boxShadow: '0 4px 12px rgba(0,0,0,0.1)',
            maxWidth: '400px',
            width: '100%',
            textAlign: 'center',
        },
        welcome: {
            fontSize: '26px',
            fontWeight: '600',
            marginBottom: '10px',
            color: '#333',
        },
        loginText: {
            fontSize: '22px',
            fontWeight: '500',
            marginBottom: '20px',
            color: '#444',
        },
        formGroup: {
            marginBottom: '15px',
            textAlign: 'left',
        },
        label: {
            display: 'block',
            marginBottom: '6px',
            fontWeight: '500',
            color: '#333',
        },
        input: {
            width: '100%',
            padding: '10px',
            border: '1px solid #ccc',
            borderRadius: '6px',
            fontSize: '14px',
        },
        button: {
            width: '100%',
            padding: '12px',
            backgroundColor: '#00796b',
            color: '#fff',
            border: 'none',
            borderRadius: '6px',
            fontSize: '16px',
            cursor: 'pointer',
            marginTop: '10px',
        },
        error: {
            color: 'red',
            fontSize: '14px',
            marginBottom: '12px',
        },
        registerText: {
            fontSize: '14px',
            marginTop: '15px',
        },
        link: {
            color: '#00796b',
            textDecoration: 'none',
            marginLeft: '4px',
        },
    };

    return (
        <div style={styles.container}>
            <div style={styles.box}>
                <div style={styles.welcome}>
                    Welcome back <span className="wave">👋</span>
                </div>
                <div style={styles.loginText}>Login</div>

                {error && <p style={styles.error}>{error}</p>}

                <form onSubmit={handleSubmit}>
                    <div style={styles.formGroup}>
                        <label style={styles.label}>Email:</label>
                        <input
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                            style={styles.input}
                        />
                    </div>
                    <div style={styles.formGroup}>
                        <label style={styles.label}>Password:</label>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                            style={styles.input}
                        />
                    </div>
                    <button type="submit" style={styles.button}>Login</button>
                </form>

                <p style={styles.registerText}>
                    Don’t have an account?
                    <a href="/register" style={styles.link}>Register</a>
                </p>
            </div>
        </div>
    );
}

export default LoginPage;
