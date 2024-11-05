import React, { useState, useEffect } from 'react';
import { Trash2 } from 'lucide-react';

const UserManagement = () => {
    const [users, setUsers] = useState([]);
    const [newUser, setNewUser] = useState({ firstName: '', lastName: '', age: '', email: '' });
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');
    const [showDeleteDialog, setShowDeleteDialog] = useState(false);
    const [updateUserId, setUpdateUserId] = useState(null); // Track user ID for updates
    const [updatedLastName, setUpdatedLastName] = useState(''); // Store updated last name

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/users');
            const data = await response.json();
            setUsers(data);
        } catch (err) {
            setError('Failed to fetch users');
            setTimeout(() => setError(''), 3000);
        }
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewUser(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/api/users', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newUser),
            });

            if (!response.ok) throw new Error('Failed to add user');

            setMessage('User added successfully!');
            setNewUser({ firstName: '', lastName: '', age: '', email: '' });
            fetchUsers();
            setTimeout(() => setMessage(''), 3000);
        } catch (err) {
            setError('Failed to add user');
            setTimeout(() => setError(''), 3000);
        }
    };

    const handleDeleteAll = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/users', {
                method: 'DELETE',
            });

            if (!response.ok) throw new Error('Failed to delete users');

            setMessage('All users deleted successfully!');
            setUsers([]);
            setShowDeleteDialog(false);
            setTimeout(() => setMessage(''), 3000);
        } catch (err) {
            setError('Failed to delete users');
            setTimeout(() => setError(''), 3000);
        }
    };

    const handleUpdateLastName = async (userId) => {
        try {
            const response = await fetch(`http://localhost:8080/api/users/${userId}/last-name`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ lastName: updatedLastName }),
            });

            if (!response.ok) throw new Error('Failed to update last name');

            setMessage('Last name updated successfully!');
            setUpdatedLastName('');
            fetchUsers();
            setTimeout(() => setMessage(''), 3000);
        } catch (err) {
            setError('Failed to update last name');
            setTimeout(() => setError(''), 3000);
        }
    };

    // Function to handle deletion of a specific user
    const handleDelete = async (userId) => {
        try {
            const response = await fetch(`http://localhost:8080/api/users/${userId}`, {
                method: 'DELETE',
            });

            if (!response.ok) throw new Error('Failed to delete user');

            setMessage('User deleted successfully!');
            fetchUsers(); // Refresh the user list
            setTimeout(() => setMessage(''), 3000);
        } catch (err) {
            setError('Failed to delete user');
            setTimeout(() => setError(''), 3000);
        }
    };

    return (
        <div className="max-w-6xl mx-auto p-6">
            <div className="mb-8">
                <h1 className="text-3xl font-bold mb-4">User Management System</h1>

                {message && (
                    <div className="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded mb-4">
                        {message}
                    </div>
                )}
                
                {error && (
                    <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
                        {error}
                    </div>
                )}

                <form onSubmit={handleSubmit} className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="firstName">
                            First Name
                        </label>
                        <input
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            id="firstName"
                            type="text"
                            name="firstName"
                            value={newUser.firstName}
                            onChange={handleInputChange}
                            required
                        />
                    </div>

                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="lastName">
                            Last Name
                        </label>
                        <input
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            id="lastName"
                            type="text"
                            name="lastName"
                            value={newUser.lastName}
                            onChange={handleInputChange}
                            required
                        />
                    </div>

                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="age">
                            Age
                        </label>
                        <input
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            id="age"
                            type="number"
                            name="age"
                            value={newUser.age}
                            onChange={handleInputChange}
                            required
                        />
                    </div>

                    <div className="mb-6">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="email">
                            Email
                        </label>
                        <input
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            id="email"
                            type="email"
                            name="email"
                            value={newUser.email}
                            onChange={handleInputChange}
                            required
                        />
                    </div>

                    <div className="flex items-center justify-between">
                        <button
                            type="submit"
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                        >
                            Add User
                        </button>

                        <button
                            type="button"
                            onClick={() => setShowDeleteDialog(true)}
                            className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline flex items-center gap-2"
                        >
                            <Trash2 className="w-4 h-4" />
                            Delete All
                        </button>
                    </div>
                </form>
            </div>

            {/* Delete Confirmation Dialog */}
            {showDeleteDialog && (
                <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
                    <div className="bg-white p-6 rounded-lg max-w-md w-full mx-4">
                        <h3 className="text-lg font-bold mb-2">Are you sure?</h3>
                        <p className="mb-4">This will delete all users!</p>
                        <div className="flex justify-end">
                            <button
                                onClick={() => setShowDeleteDialog(false)}
                                className="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline mr-2"
                            >
                                Cancel
                            </button>
                            <button
                                onClick={handleDeleteAll}
                                className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                            >
                                Delete All
                            </button>
                        </div>
                    </div>
                </div>
            )}

            {/* User List */}
            <div className="overflow-x-auto">
                {users.length > 0 ? (
                    <table className="min-w-full border border-gray-300">
                        <thead>
                            <tr>
                                <th className="border px-4 py-2">First Name</th>
                                <th className="border px-4 py-2">Last Name</th>
                                <th className="border px-4 py-2">Age</th>
                                <th className="border px-4 py-2">Email</th>
                                <th className="border px-4 py-2">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {users.map((user) => (
                                <tr key={user.id}>
                                    <td className="border px-4 py-2">{user.firstName}</td>
                                    <td className="border px-4 py-2">
                                        {updateUserId === user.id ? (
                                            <input
                                                type="text"
                                                value={updatedLastName}
                                                onChange={(e) => setUpdatedLastName(e.target.value)}
                                                className="border p-1"
                                            />
                                        ) : (
                                            user.lastName
                                        )}
                                    </td>
                                    <td className="border px-4 py-2">{user.age}</td>
                                    <td className="border px-4 py-2">{user.email}</td>
                                    <td className="border px-4 py-2">
                                        {updateUserId === user.id ? (
                                            <button
                                                onClick={() => {
                                                    handleUpdateLastName(user.id);
                                                    setUpdateUserId(null); // Reset the user ID after update
                                                }}
                                                className="bg-green-500 text-white px-2 py-1 rounded"
                                            >
                                                Update
                                            </button>
                                        ) : (
                                            <button
                                                onClick={() => setUpdateUserId(user.id)} // Set user ID for editing
                                                className="bg-blue-500 text-white px-2 py-1 rounded"
                                            >
                                                Edit Last Name
                                            </button>
                                        )}
                                        <button
                                            onClick={() => {
                                                handleDelete(user.id);
                                                fetchUsers(); // Refresh the user list
                                            }}
                                            className="bg-red-500 text-white px-2 py-1 rounded ml-2"
                                        >
                                            Delete
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                ) : (
                    <p>No users found.</p>
                )}
            </div>
        </div>
    );
};

export default UserManagement;
