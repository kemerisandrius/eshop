import HTTP from '.'

export default {
    getUser() {
        return HTTP.get('/v1/user')
    }
}
