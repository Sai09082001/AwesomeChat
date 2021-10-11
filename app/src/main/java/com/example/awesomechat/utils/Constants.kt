package com.example.awesomechat.utils



const val PREF_FILE_NAME = "Preferences"
const val DEFAULT_TIMEOUT = 30
const val DURATION_TIME_CLICKABLE = 500
const val COLOR_WARNING = "#EE2324"
const val TIME_ASK_TWO_SHOT_AUTO_DISMISS = 30000L
const val REWARD_DIAMOND_FLAG = 1111
const val REWARD_DIAMOND_INTERVAL = 30 * 60 * 1000L

object ParamIntent {
    const val PARAM_ID = "id"
    const val PARAM_PASS = "pass"
    const val PARAM_CODE = "code"
    const val PARAM_MODE = "mode"
    const val PARAM_SITE = "site"
}

object BundleKey {
    const val KEY_URI = "KEY_URI"
    const val KEY_PARAM_SITE = "KEY_PARAM_SITE"
    const val KEY_PARAM_LIST_MEMBER = "KEY_PARAM_LIST_MEMBER"
    const val IS_SKIN_BEAUTY_ON = "IS_SKIN_BEAUTY_ON"
    const val IS_HAS_MOSAIC = "IS_HAS_MOSAIC"
    const val IS_SHOW_TEMPLATE_MESSAGE = "IS_SHOW_TEMPLATE_MESSAGE"
    const val KEY_MEMBER_CODE = "KEY_MEMBER_CODE"
    const val KEY_TIME_AUTO_DISMISS = "KEY_TIME_AUTO_DISMISS"
    const val KEY_TYPE_ALERT = "KEY_TYPE_ALERT"
    const val KEY_TITLE = "KEY_TITLE"
    const val KEY_TEXT_POSITIVE = "KEY_TEXT_POSITIVE"
    const val KEY_TEXT_NEGATIVE = "KEY_TEXT_NEGATIVE"
    const val KEY_PARAM_LIST_MESSAGE = "KEY_PARAM_LIST_MESSAGE"
}

object KeyFileShare {
     const val KEY_ID_USER="KEY_ID_USER"
     const val KEY_EMAIL = "KEY_EMAIL"
     const val FILE_NAME = "file_shared"
}

object TypeAlert {
    const val ALERT_ACTION = 0
    const val ALERT_WITHOUT_ACTION = 1
    const val CONFIRM = 2
}
