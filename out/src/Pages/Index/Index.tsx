// REACT
import React from 'react'

// COMPONENETES
import Navbar from 'Components/Navbar/Navbar'
import Content from 'Components/Content/Content'

// @ts-ignore
const regs = window.REGISTRY.data

const Index: React.FC = () => {
	return (
		<>
			<main>
				<Navbar />
				<Content regs={regs} />
			</main>
		</>
	)
}
export default Index
