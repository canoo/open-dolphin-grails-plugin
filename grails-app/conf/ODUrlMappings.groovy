class ODUrlMappings {

	static mappings = {

		"/od/$pageName"(
			controller: 'app', // dirty trick: this is the subdir of the view
			view: { params.pageName }
		)

	}
}
