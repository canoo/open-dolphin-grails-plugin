import org.opendolphin.core.comm.JsonCodec
import org.opendolphin.core.server.EventBus
import org.opendolphin.core.server.ServerConnector
import org.opendolphin.core.server.ServerDolphin
import org.opendolphin.core.server.ServerModelStore
import org.opendolphin.grails.DolphinSpringBean

class OpenDolphinGrailsPlugin {
    // the plugin version
    def version = "0.12.0"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.4 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Open Dolphin Grails Plugin Plugin" // Headline display name of the plugin
    def author = "Sven Ehrke"
    def authorEmail = "sven.ehrke@sven-ehrke.de"
    def description = '''\
Supports you to dolphinize your grails applications.
'''

    // URL to the plugin's documentation
    def documentation = "https://github.com/canoo/open-dolphin-grails-plugin"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
    def organization = [ name: "Canoo Engineering AG", url: "http://www.canoo.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "https://github.com/canoo/open-dolphin-grails-plugin" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {

		dolphinEventBus(EventBus) { bean ->
			bean.scope = 'singleton'
		}

		dolphinModelStore(ServerModelStore) { bean ->
			bean.scope = 'session' // every session must have its own model store
		}

		dolphinServerConnector(ServerConnector) { bean ->
			bean.scope = 'session'  // could be shared among sessions but since the registry is set, this is safer...
			codec = new JsonCodec()
			serverModelStore = ref('dolphinModelStore')
		}

		serverDolphin(ServerDolphin, ref('dolphinModelStore'), ref('dolphinServerConnector')) { bean ->
			bean.scope = 'session'
		}

		dolphinBean(DolphinSpringBean, ref('serverDolphin'), ref('dolphinAppDirector'), ref('dolphinEventBus')) { bean ->
			bean.scope = 'session'
		}

    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { ctx ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
