// REACT
import React, { memo } from 'react'

// MATERIAL UI
import {
	Paper,
	Typography,
	TableContainer,
	TableBody,
	TableRow,
	TableCell,
} from '@material-ui/core'

// ESTILOS
import Styles from './LogData.module.scss'
import { dateFormater } from 'Utils/Tools'

interface Props {
	reg: any
}

const LogData: React.FC<Props> = (props: Props) => {
	const rows = props.reg.content
		.replace(/!</g, '\n')
		.split(/--+/)
		.filter((value: string) => value.length > 1)
		.map((rows: string) =>
			rows
				.split('|')
				.filter((cell: string) => cell.length > 2)
				.map((dataCell: string) => dataCell.trim())
		)

	return (
		<div className={Styles.container}>
			<Paper className={Styles.date}>
				<Typography style={{ display: 'flex', justifyContent: 'space-between' }}>
					<span>{dateFormater(props.reg.date)}</span> <span>{props.reg.title}</span>
				</Typography>
			</Paper>
			<TableContainer
				component={Paper}
				style={{
					padding: '30px',
					display: 'flex',
					justifyContent: 'center',
					alignItems: 'center',
					height: '360px',
				}}>
				<TableBody
					style={{
						border: '2px solid #333',
						borderRadius: '10px',
						overflow: 'hidden',
					}}>
					{rows.map((row: string[], key: number) => (
						<TableRow key={key}>
							{row.map((cell: string, keyS: number) => (
								<TableCell
									align='center'
									style={{ border: '1px solid #333', color: '#333', fontWeight: 'bold' }}
									key={keyS}>
									{cell}
								</TableCell>
							))}
						</TableRow>
					))}
				</TableBody>
			</TableContainer>
		</div>
	)
}

export default memo(LogData)
