{{/* Define a Deployment template */}}
{{ define "my-microservice.deployment" -}}
apiVersion: apps/v1
kind: Deployment
metadata:
  name: {{ .Release.Name }}-{{ .Chart.Name }}
spec:
  replicas: {{ .Values.replicas }}
  template:
    metadata:
      labels:
        app: {{ template "my-microservice.name" . }}
    spec:
      containers:
        - name: {{ template "my-microservice.name" . }}
          image: {{ .Values.image.repository }}:{{ .Values.image.tag }}
          ports:
            - containerPort: {{ .Values.containerPort }}
{{- end }}

{{/* Define a Service template */}}
{{ define "my-microservice.service" -}}
apiVersion: v1
kind: Service
metadata:
  name: {{ .Release.Name }}-{{ .Chart.Name }}
spec:
  selector:
    app: {{ template "my-microservice.name" . }}
  ports:
    - protocol: TCP
      port: {{ .Values.service.port }}
{{- end }}

{{/* Define a Pod template */}}
{{ define "my-microservice.pod" -}}
apiVersion: v1
kind: Pod
metadata:
  name: {{ .Release.Name }}-{{ .Chart.Name }}
  labels:
    app: {{ template "my-microservice.name" . }}
spec:
  containers:
    - name: {{ template "my-microservice.name" . }}
      image: {{ .Values.image.repository }}:{{ .Values.image.tag }}
      ports:
        - containerPort: {{ .Values.containerPort }}
{{- end }}

{{/* Define a Secret template */}}
{{ define "my-microservice.secret" -}}
apiVersion: v1
kind: Secret
metadata:
  name: {{ .Release.Name }}-{{ .Chart.Name }}-secret
type: Opaque
data:
  api_key: {{ .Values.apiKey | b64enc | quote }}
  db_password: {{ .Values.database.password | b64enc | quote }}
{{- end }}

{{/* Define a ConfigMap template */}}
{{ define "my-microservice.configmap" -}}
apiVersion: v1
kind: ConfigMap
metadata:
  name: {{ .Release.Name }}-{{ .Chart.Name }}-config
data:
  application.properties: |-
    # Your application configuration here
    database.url=jdbc:mysql://db-host:3306/mydb
    database.username={{ .Values.database.username }}
    # Add more properties as needed
{{- end }}













{{/*
Expand the name of the chart.
*/}}
{{- define "irrigation-chart.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "irrigation-chart.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "irrigation-chart.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Common labels
*/}}
{{- define "irrigation-chart.labels" -}}
helm.sh/chart: {{ include "irrigation-chart.chart" . }}
{{ include "irrigation-chart.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "irrigation-chart.selectorLabels" -}}
app.kubernetes.io/name: {{ include "irrigation-chart.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "irrigation-chart.serviceAccountName" -}}
{{- if .Values.serviceAccount.create }}
{{- default (include "irrigation-chart.fullname" .) .Values.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.serviceAccount.name }}
{{- end }}
{{- end }}
