package org.opendolphin.grails

import org.opendolphin.LogConfig
import org.opendolphin.core.server.EventBus
import org.opendolphin.core.server.ServerDolphin
import groovy.util.logging.Log
import org.opendolphin.core.server.action.DolphinServerAction

import java.util.logging.Level
import java.util.logging.Logger

@Log
class DolphinSpringBean {

	DolphinSpringBean(ServerDolphin dolphin, DolphinServerAction appDirector, EventBus eventBus) {
		Logger.getLogger("").level = Level.INFO
		LogConfig.noLogs()
		log.info "creating new dolphin session"
		if (!dolphin) {
			println "dolphin is null"
		}
		if (!appDirector) {
			println "appDirector is null"
		}
		dolphin.registerDefaultActions()
		dolphin.register(appDirector)
	}
}