// REACT
import React, { useContext, memo, Dispatch, SetStateAction, useState } from 'react'

// MATERIAL UI
import AppBar from '@material-ui/core/AppBar'
import Toolbar from '@material-ui/core/Toolbar'
import Typography from '@material-ui/core/Typography'
import IconButton from '@material-ui/core/IconButton'
import Dialog from '@material-ui/core/Dialog'

// ICONOS
import InfoOutlined from '@material-ui/icons/InfoOutlined'
import StarTwoTone from '@material-ui/icons/StarTwoTone'

// CONTEXTO
import MainContext from 'Context/MainContext'
import { DialogTitle, DialogContent, DialogContentText } from '@material-ui/core'
import Link from '@material-ui/core/Link'

const Navbar: React.FC = () => {
	// CONTEXTO
	const { lang } = useContext(MainContext)

	// ESTADO
	const [open, setOpen]: [number, Dispatch<SetStateAction<number>>] = useState(0)

	// ABRIR
	const openS = () => setOpen(1)
	const closeS = () => setOpen(0)

	return (
		<nav>
			<Dialog open={open === 1} onClose={closeS}>
				<DialogTitle>Desarrolado por Alex Santos</DialogTitle>
				<DialogContent>
					<DialogContentText>
						Si te gusto este proyecto me ayudarías mucho si me regalas una estrella{' '}
						{<StarTwoTone />} en mi github desde este{' '}
						<Link href='https://github.com/alexsan-dev/ipc-encrypt' title='Github'>
							Repositorio
						</Link>{' '}
						para el proyecto principal en Java.
					</DialogContentText>
				</DialogContent>
			</Dialog>
			<AppBar position='static'>
				<Toolbar style={{ background: '#1565c0' }}>
					<IconButton edge='start' color='inherit' aria-label='menu' onClick={openS}>
						<InfoOutlined />
					</IconButton>
					<Typography variant='h6'>{lang.navbar.title}</Typography>
				</Toolbar>
			</AppBar>
		</nav>
	)
}

export default memo(Navbar)
