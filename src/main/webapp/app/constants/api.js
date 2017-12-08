const API_PREFIX = '/api'

// account
export const ACCOUNT_URL = `${API_PREFIX}/account`
export const LOGIN_URL = `${ACCOUNT_URL}/login`
export const LOGOUT_URL = `${ACCOUNT_URL}/logout`
export const REGISTER_URL = `${ACCOUNT_URL}/register`
export const ACTIVATE_STATE_URL = `${ACCOUNT_URL}/state`

export const UPDATE_EMAIL_URL = `${ACCOUNT_URL}/update-email`
export const SEND_ACTIVATE_EMAIL_URL = `${ACCOUNT_URL}/activate`

export const VALIDATE_ACCOUNT_URL = `${ACCOUNT_URL}/validate`

// topics
export const TOPICS_URL = `${API_PREFIX}/topics`
export const TOPICS_PAGES_URL = `${TOPICS_URL}/pages`
export const TOPIC_URL = `${API_PREFIX}/topic`
export const TOPIC_REPLY_URL = `${TOPIC_URL}/reply`
