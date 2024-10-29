import React, { useState, useEffect } from 'react';
import { AlertCircle, UserPlus, Trash2, RefreshCw } from 'lucide-react';
import { Alert, AlertDescription } from '@/components/ui/alert';

export default function UserManagementSystem() {
  const [users, setUsers] = useState([]);
  const [error, setError] = useState('');
  const [newUser, setNewUser] = useState({
    id: '', firstName: '', lastName: '', email: '', age: ''
  });
  const [testResults, setTestResults] = useState('');

  const fetchUsers = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/users');
      const data = await response.json();
      setUsers(data);
      setError(data.length === 0 ? 'List empty, please add users first' : '');
    } catch (err) {
      setError('Error fetching users');
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  const handleAddUser = async (e) => {
    e.preventDefault();
    try {
      await fetch('http://localhost:8080/api/users', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ ...newUser, id: parseInt(newUser.id), age: parseInt(newUser.age) })
      });
      setNewUser({ id: '', firstName: '', lastName: '', email: '', age: '' });
      fetchUsers();
    } catch (err) {
      setError('Error adding user');
    }
  };

  const handleDeleteAll = async () => {
    try {
      await fetch('http://localhost:8080/api/users', { method: 'DELETE' });
      fetchUsers();
    } catch (err) {
      setError('Error deleting users');
    }
  };

  const runTests = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/users/test', { method: 'POST' });
      const result = await response.text();
      setTestResults(result);
    } catch (err) {
      setError('Error running tests');
    }
  };

  return (
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-3xl font-bold mb-8">User Management System</h1>
      
      {error && (
        <Alert variant="destructive" className="mb-4">
          <AlertCircle className="h-4 w-4" />
          <AlertDescription>{error}</AlertDescription>
        </Alert>
      )}

      <div className="grid md:grid-cols-2 gap-8">
        <div className="bg-white p-6 rounded-lg shadow-md">
          <h2 className="text-xl font-semibold mb-4">Add New User</h2>
          <form onSubmit={handleAddUser} className="space-y-4">
            <input
              type="number"
              placeholder="ID"
              className="w-full p-2 border rounded"
              value={newUser.id}
              onChange={e => setNewUser({...newUser, id: e.target.value})}
            />
            <input
              type="text"
              placeholder="First Name"
              className="w-full p-2 border rounded"
              value={newUser.firstName}
              onChange={e => setNewUser({...newUser, firstName: e.target.value})}
            />
            <input
              type="text"
              placeholder="Last Name"
              className="w-full p-2 border rounded"
              value={newUser.lastName}
              onChange={e => setNewUser({...newUser, lastName: e.target.value})}
            />
            <input
              type="email"
              placeholder="Email"
              className="w-full p-2 border rounded"
              value={newUser.email}
              onChange={e => setNewUser({...newUser, email: e.target.value})}
            />
            <input
              type="number"
              placeholder="Age"
              className="w-full p-2 border rounded"
              value={newUser.age}
              onChange={e => setNewUser({...newUser, age: e.target.value})}
            />
            <button
              type="submit"
              className="w-full bg-blue-500 text-white p-2 rounded hover:bg-blue-600 flex items-center justify-center gap-2"
            >
              <UserPlus className="h-4 w-4" />
              Add User
            </button>
          </form>
        </div>

        <div className="bg-white p-6 rounded-lg shadow-md">
          <div className="flex justify-between items-center mb-4">
            <h2 className="text-xl font-semibold">User List</h2>
            <div className="space-x-2">
              <button
                onClick={handleDeleteAll}
                className="bg-red-500 text-white p-2 rounded hover:bg-red-600 flex items-center gap-2"
              >
                <Trash2 className="h-4 w-4" />
                Delete All
              </button>
              <button
                onClick={runTests}
                className="bg-green-500 text-white p-2 rounded hover:bg-green-600 flex items-center gap-2"
              >
                <RefreshCw className="h-4 w-4" />
                Run Tests
              </button>
            </div>
          </div>
          
          <div className="space-y-4">
            {users.map(user => (
              <div key={user.id} className="border p-4 rounded">
                <h3 className="font-semibold">ID: {user.id}</h3>
                <p>Name: {user.firstName} {user.lastName}</p>
                <p>Email: {user.email}</p>
                <p>Age: {user.age}</p>
              </div>
            ))}
          </div>
        </div>
      </div>
      
      {testResults && (
        <div className="mt-8 bg-white p-6 rounded-lg shadow-md">
          <h2 className="text-xl font-semibold mb-4">Test Results</h2>
          <pre className="bg-gray-100 p-4 rounded">{testResults}</pre>
        </div>
      )}
    </div>
  );
}
