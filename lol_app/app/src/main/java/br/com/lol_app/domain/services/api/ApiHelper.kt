package br.com.lol_app.domain.services.api

import javax.inject.Inject

class ApiHelper @Inject constructor(private val api: DbApi) : ApiHelperContract {
}