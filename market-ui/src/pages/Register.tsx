import React, { useState } from 'react'
import { registerUser } from '../api'
import { useNavigate } from 'react-router-dom'
import { useNotification } from '../hooks/useNotification'

export default function RegisterPage() {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const navigate = useNavigate()
  const { notify, Notification } = useNotification()

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault()
    try {
      await registerUser({ username, password })
      notify('Registered successfully. Please login.')
      navigate('/login')
    } catch (e: any) {
      notify('Registration failed')
    }
  }

  return (
    <div className="max-w-md mx-auto">
      <h1 className="text-2xl mb-4">Register</h1>
      <form onSubmit={handleSubmit} className="space-y-3 bg-white p-4 rounded" data-test-id="register-form">
        <div>
          <label className="block text-sm">Username (4-25 chars)</label>
          <input value={username} onChange={e => setUsername(e.target.value)} className="w-full p-2 border rounded" minLength={4} maxLength={25} data-test-id="username"/>
        </div>
        <div>
          <label className="block text-sm">Password (8-50 chars)</label>
          <input type="password" value={password} onChange={e => setPassword(e.target.value)} className="w-full p-2 border rounded" minLength={8} maxLength={50} data-test-id="password"/>
        </div>
        <div>
          <button className="px-3 py-1 bg-indigo-600 text-white rounded" data-test-id="register">Register</button>
        </div>
      </form>
      <Notification />
    </div>
  )
}
