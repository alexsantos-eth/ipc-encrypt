import { createContext, Context } from 'react'

// STRINGS
import Strings from 'Lang/Strings.json'

// KEYS
interface ContextProps {
	lang: ILangPackage
	langCode: string
}

// VALOR POR DEFECTO
const DefContext: ContextProps = { lang: Strings.es, langCode: 'ES' }

// CONTEXTO
const MainContext: Context<ContextProps> = createContext(DefContext)

export default MainContext
