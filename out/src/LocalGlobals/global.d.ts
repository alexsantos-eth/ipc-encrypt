interface ILangs {
	es: ILangPackage
}

interface ILangPackage {
	navbar: {
		title: string
	}
	steps:{
		next:string
		back:string
	}
}
