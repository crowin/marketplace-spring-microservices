import {
  NewUserRequest, LoginRequest, TokenAuthResponse,
  BasicResponseListDataProductDto, ItemCartDto, BasicResponseCartDto,
  BasicResponseOrderDto, BasicResponseListDataOrderDto
} from './types'

const USERS_BASE = '/users'
const MARKET_BASE = '/market'

function getToken() {
  return localStorage.getItem('token') || ''
}

function authHeaders() {
  const token = getToken()
  return token ? { Authorization: `Bearer ${token}` } : {}
}

async function request(path: string, options: RequestInit = {}) {
  const headers = { 'Content-Type': 'application/json', ...options.headers }
  const opts = { ...options, headers }
  const res = await fetch(path, opts)
  const text = await res.text()
  const contentType = res.headers.get('Content-Type') || ''
  let body: any = text
  try {
    if (contentType.includes('application/json')) body = JSON.parse(text)
  } catch (e) {}
  if (!res.ok) {
    throw new Error((body && body.error) || res.statusText || 'Request failed')
  }
  return body
}

/* Users */
export async function registerUser(data: NewUserRequest) {
  return request(`${USERS_BASE}/register`, { method: 'POST', body: JSON.stringify(data) })
}

export async function loginUser(data: LoginRequest): Promise<TokenAuthResponse> {
  const body = await request(`${USERS_BASE}/login`, { method: 'POST', body: JSON.stringify(data) })
  if (body && body.token) {
    localStorage.setItem('token', body.token)
  }
  return body
}

/* Products */
export async function getProducts(page = 0, size = 5): Promise<BasicResponseListDataProductDto> {
  return request(`${MARKET_BASE}/products?page=${page}&size=${size}`, { method: 'GET', headers: authHeaders() })
}

/* Cart */
export async function getCart(): Promise<BasicResponseCartDto> {
  return request(`${MARKET_BASE}/cart`, { method: 'GET', headers: authHeaders() })
}

export async function addToCart(item: ItemCartDto): Promise<BasicResponseCartDto> {
  return request(`${MARKET_BASE}/cart`, { method: 'PUT', body: JSON.stringify(item), headers: authHeaders() })
}

export async function removeFromCart(productId: number) {
  return request(`${MARKET_BASE}/cart/product?productId=${productId}`, { method: 'DELETE', headers: authHeaders() })
}

export async function clearCart() {
  return request(`${MARKET_BASE}/cart`, { method: 'DELETE', headers: authHeaders() })
}

/* Orders */
export async function placeOrder(): Promise<BasicResponseOrderDto> {
  return request(`${MARKET_BASE}/orders/`, { method: 'POST', headers: authHeaders() })
}

export async function getOrders(page = 0, size = 5): Promise<BasicResponseListDataOrderDto> {
  return request(`${MARKET_BASE}/orders/?page=${page}&size=${size}`, { method: 'GET', headers: authHeaders() })
}

export function logout() {
  localStorage.removeItem('token')
}
