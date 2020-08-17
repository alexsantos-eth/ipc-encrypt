// REACT
import React, { useState, memo, useContext } from 'react'

// ESTILOS
import Styles from './Content.module.scss'

// MATERIAL
import Button from '@material-ui/core/Button'
import MobileStepper from '@material-ui/core/MobileStepper'
import KeyboardArrowLeft from '@material-ui/icons/KeyboardArrowLeft'
import KeyboardArrowRight from '@material-ui/icons/KeyboardArrowRight'
import LogData from 'Components/LogData/LogData'

// CONTEXTO
import MainContext from 'Context/MainContext'

interface IProps {
	regs: any
}

const Content: React.FC<IProps> = (props: IProps) => {
	// CONTEXTO
	const { lang } = useContext(MainContext)

	// ESTADO
	const [activeStep, setActiveStep] = useState(0)

	// SIGUIENTE
	const handleNext = () => setActiveStep(prevActiveStep => prevActiveStep + 1)

	// ANTERIOR
	const handleBack = () => setActiveStep(prevActiveStep => prevActiveStep - 1)

	return (
		<>
			<div className={Styles.container}>
				<div
					className={Styles.slider}
					style={{
						width: `${props.regs.length * 100}%`,
						transform: `translateX(${-1 * activeStep * (100 / props.regs.length)}%)`,
					}}>
					{props.regs.map((reg: any, key: number) => (
						<LogData key={key} reg={reg} />
					))}
				</div>
			</div>
			<MobileStepper
				style={{ background: '#0d47a1', color: 'white' }}
				className={Styles.stepper}
				variant={props.regs.length > 30 ? 'text' : 'dots'}
				steps={props.regs.length}
				position='static'
				activeStep={activeStep}
				nextButton={
					<Button
						style={{
							color: 'white',
							opacity: `${activeStep === props.regs.length - 1 ? '0.4' : '1'}`,
						}}
						size='large'
						onClick={handleNext}
						disabled={activeStep === props.regs.length - 1}>
						{lang.steps.next}
						<KeyboardArrowRight />
					</Button>
				}
				backButton={
					<Button
						style={{ color: 'white', opacity: `${activeStep === 0 ? '0.4' : '1'}` }}
						size='large'
						onClick={handleBack}
						disabled={activeStep === 0}>
						<KeyboardArrowLeft />
						{lang.steps.back}
					</Button>
				}
			/>
		</>
	)
}

export default memo(Content)
