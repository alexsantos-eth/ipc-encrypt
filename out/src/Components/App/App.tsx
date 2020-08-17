// REACT
import React from 'react'

// STRINGS
import Strings from 'Lang/Strings.json'

// CONTEXTO
import MainContext from 'Context/MainContext'

// PAGINAS
import Index from 'Pages/Index/Index'

// ESTADO
interface AppState {
	langCode: string
	lang: ILangPackage
}

// ESTADO POR DEFECTO
const DefState: AppState = {
	langCode: 'ES',
	lang: Strings.es,
}

const App: React.FC = () => {
	return (
		<MainContext.Provider value={{ ...DefState }}>
			<Index />
		</MainContext.Provider>
	)
}

export default App
