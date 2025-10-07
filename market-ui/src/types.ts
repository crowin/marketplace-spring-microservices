export interface NewUserRequest { username: string; password: string }
export interface LoginRequest { username: string; password: string }
export interface TokenAuthResponse { token: string; type: string }

export interface ProductDto { id: number; title: string; price: number }
export interface ItemDto { product: ProductDto; quantity: number; totalPrice: number }
export interface CartDto { items: ItemDto[]; totalPrice: number }
export interface BasicResponseCartDto { data?: CartDto; error?: string; errorCode?: string }
export interface ItemCartDto { itemId: number; quantity?: number }

export interface OrderDto { id: number; userOrderId?: string; createdAt?: string; finishedAt?: string | null; items?: { products: ItemDto[]; totalPrice: number }; status?: string }
export interface ListDataProductDto { items: ProductDto[]; current: number; totalPages: number; totalItems: number }
export interface BasicResponseListDataProductDto { data?: ListDataProductDto; error?: string; errorCode?: string }
export interface BasicResponseOrderDto { data?: OrderDto; error?: string; errorCode?: string }
export interface ListDataOrderDto { items: OrderDto[]; current: number; totalPages: number; totalItems: number }
export interface BasicResponseListDataOrderDto { data?: ListDataOrderDto; error?: string; errorCode?: string }
