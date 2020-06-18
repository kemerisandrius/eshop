import * as Yup from 'yup'
import i18n from '../language/i18n'

Yup.setLocale({
    mixed: {
        default: i18n.t('validations:required'),
        required: i18n.t('validations:required')
    },
    string: {
        min: ({min}) => i18n.t('validations:required', {min}),
        max: ({max}) => i18n.t('validations:required', {max})
    },
    number: {
        min: ({min}) => i18n.t('validations:numberMin', {min}),
    }
})
