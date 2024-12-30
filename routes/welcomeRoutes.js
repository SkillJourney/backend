import express from "express"
import { welcome } from "../controllers/welcomeController.js"

const router = express.Router()

router.get("/", welcome)

export default router
